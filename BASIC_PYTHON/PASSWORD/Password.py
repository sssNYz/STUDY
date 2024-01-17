enterPass= 9 
checkPass= 9 
i=0

while i<=3:
    password=input("EnternewPassword(max8):") 
    while len(password) > enterPass:
        print("Thenumbero fcharactersexceedsthelimit.Pleaseentera newpassword.") 
        password=input("EnternewPassword(max8):")
    for n in range(3):
        n += 1
        checkPass = input("{}EnterPassword: ".format(n))
        if checkPass == password:
            print("Passwordcorrect!!!")
            n+=1
            i=0
        else:
            print("Password not correct!!!")
            i+=1
    if i==3:
        break
    print("Please setup new password") 
print("Close Program")
