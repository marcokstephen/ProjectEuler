import math

#number = 600851475143

def findBiggestPF(number):
    biggestPrime = 0
    primeToTest = 2
    while (number != 1):
        if (number%primeToTest == 0):
            number /= primeToTest
            if (primeToTest > biggestPrime):
                biggestPrime = primeToTest
        else:
            primeToTest = nextPrime(primeToTest)
    print biggestPrime
    
def nextPrime(n):
    currentPrime = n+1
    if (isPrime(currentPrime)):
        return currentPrime
    else:
        return nextPrime(currentPrime)

def isPrime(n):
    numToTest = 2
    maxTest = math.sqrt(n)
    while (numToTest <= maxTest):
        if (n%numToTest == 0):
            return False
        else:
            numToTest += 1
    return True