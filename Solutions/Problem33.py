def simplifies(num,den):
    strNum = str(num)
    strDen = str(den)
    firstNum = int(strNum[0])
    secondNum = int(strNum[1])
    firstDen = int(strDen[0])
    secondDen = int(strDen[1])
    newNum = 0
    newDen = 0
    if (secondDen == 0 and secondNum == 0):
        newNum = 0
    elif (firstNum == firstDen):
        newNum = secondNum
        newDen = secondDen
    elif (firstNum == secondDen):
        newNum = secondNum
        newDen = firstDen
    elif (secondNum == firstDen):
        newNum = firstNum
        newDen = secondDen
    elif (secondNum == secondDen):
        newNum = firstNum
        newDen = firstDen
    if (newDen == 0):
        return False
    elif (float(newNum)/float(newDen) == float(num)/float(den)):
        return True
    else:
        return False

for x in range (10,100):
    for y in range (10,100):
        if (x!=y and (x < y) and simplifies(x,y)):
            print x,y