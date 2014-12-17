# calculates sum of digits in a number
def sumDigits(num):
    currentSum = 0
    while num != 0:
        currentSum += (num%10)
        num = int(num/10)
    return currentSum

# finds both the numerator and denominator, but only returns
# numerator because that is what the question is asking
def findConvergentNumerator(iteration):
    numerator = 1
    if iteration % 3 == 0:
        denominator = (iteration/3)*2
    else:
        denominator = 1

    for i in range(iteration,1,-1):

        if (i-1) % 3 == 0:
            denominatorMultiplier = ((i-1)/3)*2
            numerator += (denominator*denominatorMultiplier)
        else:
            numerator += denominator

        if (i > 2):
            # swap numerator and denominator (division by fraction)
            tempNumerator = numerator
            numerator = denominator
            denominator = tempNumerator

    numerator += denominator
    return numerator

print sumDigits(findConvergentNumerator(100))