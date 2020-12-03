"""
Script that allows to retrieve instance of star battle game by their web interface.

To run this script, selenium library and ChromeDriver must be instaled.
We recommend to run the script with parametr n=12
Example: python star_battle_retrieval.py -n=12
"""

import sys

from selenium import webdriver
from time import sleep 
from re import findall
from math import sqrt

try:
    number_of_instances = int(findall(r"-n=(\d+)", sys.argv[1])[0])
except IndexError:
    print("Insert -n parameter")
    sys.exit(1)


def create_matrix(rows: int, columns: int, list: list) -> list:
    """
        Function that creates a rows x columns matrix by a given list

        :Args:
        - rows: number of rows
        - columns: number of column
        - list: list that must be converted in a matrix
    """
    mat = []
    for i in range(rows):
        row_list = []
        for j in range(columns):
            row_list.append(list[rows * i + j])
        mat.append(row_list)
    return mat


def generate_instances():
    """
        Function that generates automatically data.dzn file from some intances of star battle puzzle taken on the site.
    """

    browser=webdriver.Chrome()

    for i in range(number_of_instances):
        browser.get("https://www.puzzle-star-battle.com/?size="+str(i))
        instance = list(findall("var task = '(.*?)'", browser.page_source)[0].split(','))
        for j in range(0, len(instance)): 
            instance[j] = int(instance[j])

        sleep(1)

        element = browser.find_element_by_class_name("puzzleInfo")
        if i < 9:
            number_of_stars = int(findall(r"/(\d+)", element.text)[0])
        else:
            number_of_stars = int(findall(r"(\d+)", element.text)[0])
        size = int(sqrt(len(instance)))

        with open("../data/data"+str(i)+".dzn", "w") as file:
            file.write("num_stars = " + str(number_of_stars) + ";\n")
            file.write("dim = " + str(size) + ";\n")
            sectors = "[|\n"
            matrix = create_matrix(size, size, instance)
            for row in  matrix:
                sectors += str(row)[1:-1] + "|\n"
            sectors += "];"
            file.write("sectors = " + sectors)


    browser.close()

if __name__ == "__main__":
    generate_instances()
