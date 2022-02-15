from email import message
from multiprocessing import context
from socket import socket
import zmq

context = zmq.Context()
socket = context.socket(zmq.REP)
socket.bind("tcp://*:2222")
while True:
    message = socket.recv()
    socket.send("ACK")