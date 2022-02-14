from email import message
from multiprocessing import context
from socket import socket
import time
import zmq


context = zmq.Context()
socket = context.socket(zmq.REP)
socket.bind("tcp://*:5555")

while True:
    message = socket.recv()
    print("Received request: %s" % message)

    time.sleep(1)
    socket.send(b"World")