#!/bin/bash
SUM=0
for i in {1..1000}
do
	if (($i%3 == 0))&&(($i%5 == 0));
	then
		((SUM = SUM + i))
	fi
done
echo $SUM
exit
