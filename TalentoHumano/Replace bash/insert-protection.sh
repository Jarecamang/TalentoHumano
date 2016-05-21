#!/bin/bash

dir="../web/$1"
base=$(cat "$2")
replace=$(cat "$3")
replace="${replace}""${base}"
echo "$replace"
c=0
for f in "$dir"/*.xhtml
do
#	echo "new$c.xhtml"
	content=$(cat "$f")	
	sed -i "s|${base}|${replace}|" "$f"
	c=$((c+1))
	echo $c
done
