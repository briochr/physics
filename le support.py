import discord
from discord.ext import commands
import asyncio
import os
import re
import difflib

intents = discord.Intents.default()
intents.members = True
intents.message_content = True
intents.voice_states = True

bot = commands.Bot(command_prefix=':', description='none', intents=intents)

def search(filename, directory='.'):
    files = os.listdir(directory)
    matchs = difflib.get_close_matches(filename, files, n=1, cutoff=0.00001)
    return matchs[0] if matchs else None

async def play_sound(ctx, sound_file):
    try:
        if ctx.author.voice is None or ctx.author.voice.channel is None:
            await ctx.send("You need to be in a voice channel to use this command.")
            return

        voice_channel = ctx.author.voice.channel
        vc = await voice_channel.connect()

        source = discord.FFmpegPCMAudio(sound_file)

        vc.play(source)

        while vc.is_playing():
            await asyncio.sleep(1)

        await vc.disconnect()

    except Exception as e:
        print(e)
        await ctx.send("An error occurred while playing the sound.")

@bot.command()
async def play(ctx, soundName):
    sound = search(soundName)
    if sound:
        await play_sound(ctx, sound)
    else:
        await ctx.send("Sound not found.")

@bot.command()
async def sayHello(ctx):
    await ctx.channel.send('Calm down bro, je fonctionne.')

bot.run('le token')
