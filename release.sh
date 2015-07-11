#!/bin/bash

program="gravity"

date_string=`date "+%Y%m%d"`

output_folder_short="${program}_$date_string"
output_folder="./$output_folder_short"

zip_file_name_short="$output_folder_short.zip"
zip_file_name="./$zip_file_name_short"


if [ -d "$output_folder" ]; then
	rm -r $output_folder
fi

if [ -f "$zip_file_name" ]; then
	rm $zip_file_name
fi


make clean


mkdir -p $output_folder/src/$program
cp -r ./*.java ./Makefile ./release.sh $output_folder/src/$program

mkdir -p $output_folder/src/$program/scripts
cp -r ./scripts/* $output_folder/src/$program/scripts
#cp -r ./scripts/lagrangian_points $output_folder/src/$program/scripts

cp -r ./lib $output_folder/src/$program

mkdir -p $output_folder/src/$program/build

cp ./start.* ./GenerateMakefile.cs $output_folder/src/$program

#=================================================================

make


mkdir -p $output_folder/bin/$program
cp -r ./build/gravity/*.class $output_folder/bin/$program
cp -r ./lib $output_folder/bin

mkdir -p $output_folder/bin/scripts
cp -r ./scripts/* $output_folder/bin/scripts
#cp -r ./scripts/lagrangian_points $output_folder/bin/scripts

cp ./start.* $output_folder/bin

zip -r $zip_file_name_short $output_folder_short

rm -r $output_folder

