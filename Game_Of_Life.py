# -*- coding: utf-8 -*-
"""
Created on Fri Feb 19 19:37:36 2021

Program to model Conway's Game of Life 

@author: cjcode975
"""

import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as anni

np.random.seed()

class CGL:
    
    #Initialise a game
    def __init__(self,nRows, nCols):
        self.nRows = nRows
        self.nCols = nCols 
        self.board = np.zeros((nRows,nCols),dtype=int)
        return
    
    #Randomly fill the board    
    def randFill(self,numAlive):
        x = [1]*numAlive + [0]*(self.nRows*self.nCols-numAlive)        
        np.random.shuffle(x)
        self.board = np.asarray(x,dtype=int).reshape(self.nRows,self.nCols)
        return 
    
    #Set a position on the board to a given value
    def setPos(self, i, j, val):
        self.board[i,j] = val
        return
    
    #Calculate the state of a given cell at the end of the timestep
    def cellAtNextStep(self, i, j):        
        minx = max(i-1,0)
        miny = max(j-1,0)
        maxx = min(i+2,self.nRows)
        maxy = min(j+2,self.nCols)
        x = np.sum(self.board[minx:maxx,miny:maxy]) - self.board[i,j]
        if self.board[i,j] == 0: # cell is currently dead
            if x == 3:
                return 1 #comes to life if there are exactly 3 alive neighbours
            return 0 #otherwise stays dead
        if x==2 or x==3: #alive with 2 or 3 aive neighbours stays alive
            return 1
        return 0 #otherwise dies   
     
    #calculate the state of the board at the end of a timestep
    def stepTime(self):
        tempBoard = np.zeros((self.nRows,self.nCols),dtype=int)
        for i in range(self.nRows):
            for j in range(self.nCols):
                tempBoard[i,j] = self.cellAtNextStep(i,j)
        self.board = tempBoard
        self.drawBoard()
        return            
    
    #Draw the current board
    def drawBoard(self):
        plt.imshow(self.board)
        plt.show()       
    
    #Get the current board
    def getBoard(self):
        return self.board
      
    #Run a simulation of the game, printing the state to file at each timestep
    def simulate(self, nSteps, fname):
        f = open(fname+".txt","w")
        f.write(str(self.board.tolist())+"\n")
        for i in range(nSteps):
            self.stepTime()
            f.write(str(self.board.tolist())+"\n")
        f.close()
            