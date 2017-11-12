import random


if __name__ == '__main__':
    pass




def game():    #get guess from input
    my_number = random.randint(1,50)
    counter = 0
    while counter < 5:
        try:
            guess = int(input("Guess your number between 1~50: \n"))
        except ValueError:
            print("The input is not a number, please try again!")
        #compare with my_number
        else:
            if my_number > guess:
                print("Your should try a higher number:")
                counter += 1
            elif my_number < guess:
                print("You should try a lower number:")
                counter += 1
            else:
                print("Yep, you got the number {}".format(my_number))
                break
    else:
        print("You have used all your chances, the number is {}!".format(my_number))
    play_again=input("You wanna play again? (Y/N)")
    if play_again == "Y":
        game()
    elif play_again == "N":
        print("lata!")


game()
    #print Result