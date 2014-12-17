from fractions import Fraction

#returns the expansion of the n'th iteration as a Fraction
def iterationApproximation(iteration):
    numerator = 1
    denominator = 2
    for i in range(1,iteration):
        numerator += (denominator*2)

        #swapping the variables (division by a fraction)
        tempNumerator = numerator
        numerator = denominator
        denominator = tempNumerator

    numerator += denominator
    return Fraction(numerator,denominator)

#counts the number of digits in a number
def numOfDigits(num):
    numDigits = 0
    while (num != 0):
        numDigits += 1
        num = (int)(num/10)
    return numDigits


numFractionsMoreNumeratorDigits = 0

for i in range (1, 1001):
    approxFraction = iterationApproximation(i)
    if (numOfDigits(approxFraction.numerator) > numOfDigits(approxFraction.denominator)):
        numFractionsMoreNumeratorDigits += 1

print numFractionsMoreNumeratorDigits
