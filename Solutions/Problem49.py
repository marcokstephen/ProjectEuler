import math
import itertools

def permute(n):
    a = n/1000
    b = (n - a*1000)/100
    c = (n- a*1000 - b*100)/10
    d = (n- a*1000 - b*100 - c*10)
    return list(itertools.permutations([a,b,c,d]))

def isPrime(n):
    max = math.sqrt(n)
    currentTest = 2
    while(currentTest <= max):
        if (n%currentTest == 0):
            return False
        currentTest += 1
    return True

def nextPrime(n):
    if (n == 2):
        return 3
    currentTest = n+2
    while(True):
        if(isPrime(currentTest)):
            return currentTest
        currentTest += 2
        
def tupleToNum(tup):
    ans = 0
    ans = tup[0]*1000+tup[1]*100+tup[2]*10+tup[3]
    return ans

def myMember(permList,num):
    numList = []
    for x in range(0,24):
        temp = tupleToNum(permList.pop(0))
        numList.append(temp)
    if (numList.count(num) > 0):
        return True
    return False
        
def isAnswer(n):
    permList = permute(n)
    for x in range(1,22):
        num = tupleToNum(permList.pop(1))
        if (isPrime(num)):
            difference = num - n
            if ((difference > 0) and isPrime(n+2*difference) and myMember(permute(n),n+2*difference)):
                print n,n+difference,n+2*difference
                return True
    return False

test = nextPrime(1487)
while(test < 10000):
    if (isAnswer(test)):
        break;
    test = nextPrime(test)
