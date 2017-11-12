
if __name__ == '__main__':
    pass

shopping_list = []

print("this is a simple python project to brush up my python.")
print("Now, lets enter some item values and enter 'Done' when you finish")

while True:
    new_item = input ("enter the item:")
    if new_item == "Done":
        break
    shopping_list.append(new_item)
    
    for i in shopping_list:
        print(i)