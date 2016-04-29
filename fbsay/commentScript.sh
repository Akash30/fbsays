#!/bin/bash
FILE1="./comments.txt"
FILE2="./comments_local.txt"
diff $FILE1 $FILE2  > diff.txt
counter=0
while read x
do
    counter=$(($counter + 1))
    if [ $counter -gt 1 ]
    then
        if [ "$x" = "---" ]
        then
            continue
        fi
        if [ "${x:0:1}" = "<" ] || [ "${x:0:1}" = "Y" ]
        then   
            y=$(echo $x | tr -d '<>,')
            say $y
        fi
    fi
done < diff.txt
cat $FILE1 > $FILE2
