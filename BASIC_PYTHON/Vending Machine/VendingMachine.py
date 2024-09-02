import tkinter as tk
from tkinter import ttk, messagebox 
from PIL import Image, ImageTk
import requests #use for import picture from HTTP
from io import BytesIO


class ThaiVendingMachine:
    def __init__(self, master): #__init__ is constructor  #self is instance object) #maser is reference to root (tk)  #this function use for default setting 
        self.master = master
        self.master.title("อาหารไทยแซบๆ")
        self.master.geometry("1600x900") #set window size
        self.master.configure(bg="#f0f0f0")

        self.style = ttk.Style()
        self.style.theme_use("clam")
        self.style.configure("TButton", font=("Arial", 14), padding=10)
        self.style.configure("TLabel", font=("Arial", 14), background="#f0f0f0")

        self.current_state = "main_menu"
        self.selected_item = None
        self.customizations = {}
        self.total_price = 0
        self.balance = 0

        self.main_frame = ttk.Frame(self.master, padding="20 20 20 20", style="TFrame")
        self.main_frame.pack(fill=tk.BOTH, expand=True)

        self.menu_items = {
            "ข้าวผัด": 45,
            "ผัดกะเพรา": 59,
            "ต้มยำกุ้ง": 80
        }

        self.load_images() #load img to show menu
        self.show_main_menu() #load detail menu to show menu

    def load_images(self): #set url and loop call load_and_resize_image function 
        
        image_urls = {
            "ข้าวผัด": "https://www.maggi.co.th/sites/default/files/srh_recipes/a1b6cab9710d963ab0d30f62e5d3a88a.jpeg",
            "ผัดกะเพรา": "https://images.aws.nestle.recipes/original/494645db6798945b3c628be37b75241d_กะเพราเจ_new.jpg",
            "ต้มยำกุ้ง": "https://d3h1lg3ksw6i6b.cloudfront.net/media/image/2023/04/24/5608757681874e1ea5df1aa41d5b2e3d_How_To_Make_Tom_Yam_Kung_The_Epitome_Of_Delicious_And_Nutritious_Thai_Cuisine3.jpg",
        }
        self.images = {item: self.load_and_resize_image(url) for item, url in image_urls.items()} #loop for load img

    def load_and_resize_image(self, url): #load and resize img, then convert to tk PhotoImage
        try:
            response = requests.get(url) #get img from url
            image = Image.open(BytesIO(response.content))
            image = image.resize((200, 150), Image.LANCZOS)
            return ImageTk.PhotoImage(image)
        except Exception as e:
            print(f"Error loading image from {url}: {e}") #if error to load img
            # Return a blank image in case of error
            return ImageTk.PhotoImage(Image.new("RGB", (200, 150), color="lightgray"))

    def show_main_menu(self):
        self.clear_frame() #call clear_frame function 
        self.current_state = "main_menu"

        title = ttk.Label(self.main_frame, text="อาหารไทยแซบๆ", font=("Arial", 28, "bold"), background="#f0f0f0")
        title.pack(pady=20)

        menu_frame = ttk.Frame(self.main_frame, style="TFrame")
        menu_frame.pack(pady=20)

        for idx, (item, price) in enumerate(self.menu_items.items()):#this is output --> [(0, ("ข้าวผัด", 45)), (1, ("ผัดกะเพรา", 59)), (2, ("ต้มยำกุ้ง", 80))]
            #loop for get menu and cost for show in frame
            item_frame = ttk.Frame(menu_frame, style="TFrame")
            item_frame.grid(row=0, column=idx, padx=20, pady=10)

            image_label = ttk.Label(item_frame, image=self.images[item], background="#f0f0f0")
            image_label.pack()

            ttk.Label(item_frame, text=f"{item}", font=("Arial", 16, "bold"), background="#f0f0f0").pack()
            ttk.Label(item_frame, text=f"{price} บาท", font=("Arial", 14), background="#f0f0f0").pack()
            ttk.Button(item_frame, text="เลือก", command=lambda i=item: self.select_item(i)).pack(pady=10) #lambda function for call select_item function

    def select_item(self, item):
        self.selected_item = item #update item name that user select
        self.total_price = self.menu_items[item] #update price 
        self.customizations = {} #empty 
        self.show_customization()

    def show_customization(self):
        self.clear_frame()
        self.current_state = "customization" #update state 

        title = ttk.Label(self.main_frame, text=f"ปรุง{self.selected_item}", font=("Arial", 24, "bold"), background="#f0f0f0")
        title.pack(pady=20)

        if self.selected_item == "ข้าวผัด": #basic if-else 
            self.show_rice_type_options()
        elif self.selected_item == "ผัดกะเพรา":
            self.show_rice_type_options()
        elif self.selected_item == "ต้มยำกุ้ง":
            self.show_tom_yum_options()

    def show_rice_type_options(self):
        ttk.Label(self.main_frame, text="เลือกชนิดข้าว:", font=("Arial", 18)).pack(pady=10)
        options = [("ข้าวหอมมะลิ", 0), ("ข้าวกล้อง", 10), ("ข้าวเหนียว", 30)]
        self.create_option_buttons(options, self.select_rice_type) #call create_option_buttons for create buttons that can call another function next (select_rice_type)

    def select_rice_type(self, option, price):
        self.customizations["ชนิดข้าว"] = option #add selection in to customizations{}
        self.total_price += price #update price
        if self.selected_item == "ข้าวผัด":  #call next function
            self.show_fried_rice_ingredients()
        elif self.selected_item == "ผัดกะเพรา":
            self.show_basil_ingredients()

    def show_fried_rice_ingredients(self):
        self.clear_frame()
        ttk.Label(self.main_frame, text="เพิ่มส่วนผสม:", font=("Arial", 18)).pack(pady=10)
        options = [("ไข่ไก่", 5), ("ไข่เป็ด", 10), ("ไข่ปลาคาเวียร์", 500)]
        self.create_option_buttons(options, self.select_fried_rice_ingredient)

    def select_fried_rice_ingredient(self, option, price):
        self.customizations["เพิ่มวัตถุดิบ"] = option
        self.total_price += price
        self.show_packaging_options()

    def show_basil_ingredients(self):
        self.clear_frame()
        ttk.Label(self.main_frame, text="เพิ่มวัตถุดิบ:", font=("Arial", 18)).pack(pady=10)
        options = [("หมู", 5), ("ไก่", 10), ("วาร์กิล", 500)]
        self.create_option_buttons(options, self.select_basil_ingredient)

    def select_basil_ingredient(self, option, price):
        self.customizations["ส่วนผสม"] = option
        self.total_price += price
        self.show_spiciness_options()

    def show_spiciness_options(self):
        self.clear_frame()
        ttk.Label(self.main_frame, text="เลือกระดับความเผ็ด:", font=("Arial", 18)).pack(pady=10)
        options = [("ผัดพอดีๆ", 0), ("เผ็ดร้อนท้อง", 0), ("เผ็ดแสบดาก", 0)]
        self.create_option_buttons(options, self.select_spiciness)

    def select_spiciness(self, option, price):
        self.customizations["Spiciness"] = option
        self.show_packaging_options()
#----------------------------------------------------------------------------------------------------------
    def show_tom_yum_options(self):
        self.clear_frame()
        ttk.Label(self.main_frame, text="เพิ่มวัตถุดิบ:", font=("Arial", 18)).pack(pady=10)
        options = [("กุ้ง", 0), ("ลอปสเตอร์", 500)]
        self.create_option_buttons(options, self.select_tom_yum_ingredient)

    def select_tom_yum_ingredient(self, option, price):
        self.customizations["ส่วนผสม Ingredient"] = option
        self.total_price += price
        self.show_spiciness_options()

    def show_packaging_options(self):
        self.clear_frame()
        ttk.Label(self.main_frame, text="เลือกภาชนะ:", font=("Arial", 18)).pack(pady=10)
        options = [("ถุงพลาสติก", 10), ("กล่องกระดาษ", 20), ("นำภาชนะมาเอง", -10)]
        self.create_option_buttons(options, self.select_packaging)
#----------------------------------------------------------------------------------------------------------
        
    def select_packaging(self, option, price):
        self.customizations["ภาชนะ"] = option
        self.total_price += price
        self.show_order_summary()

    def show_order_summary(self):
        self.clear_frame()
        self.current_state = "order_summary"

        ttk.Label(self.main_frame, text="สรุปรายการอาหาร", font=("Arial", 24, "bold")).pack(pady=20)
        ttk.Label(self.main_frame, text=f"ชื่ออาหาร: {self.selected_item}", font=("Arial", 18)).pack(pady=5)
        
        for key, value in self.customizations.items(): #loop show customization that user all select
            ttk.Label(self.main_frame, text=f"{key}: {value}", font=("Arial", 18)).pack(pady=5)
        
        ttk.Label(self.main_frame, text=f"Total Price: {self.total_price} บาท", font=("Arial", 20, "bold")).pack(pady=10)

        button_frame = ttk.Frame(self.main_frame)
        button_frame.pack(pady=20)
        ttk.Button(button_frame, text="ไม่แดกแล้ว!", command=self.show_main_menu).pack(side=tk.LEFT, padx=20) #is cancel and go main menu
        ttk.Button(button_frame, text="ชำระเงิน", command=self.show_payment).pack(side=tk.RIGHT, padx=20)#is go next to payment 

    def show_payment(self):
        self.clear_frame()
        self.current_state = "payment"

        ttk.Label(self.main_frame, text="เติมเงิน", font=("Arial", 24, "bold")).pack(pady=20)
        ttk.Label(self.main_frame, text=f"ราคารวม: {self.total_price} บาท", font=("Arial", 20)).pack(pady=10) #a price that user must to pay
        ttk.Label(self.main_frame, text=f"เงินในบัญชี: {self.balance} บาท", font=("Arial", 20)).pack(pady=10)#a balance that user have 

        coin_frame = ttk.Frame(self.main_frame)
        coin_frame.pack(pady=20)

        coin_values = [1, 5, 10, 20, 50, 100]
        for idx, value in enumerate(coin_values):
            ttk.Button(coin_frame, text=f"{value} บาท", 
                       command=lambda v=value: self.insert_coin(v)).grid(row=idx//3, column=idx%3, padx=10, pady=5)

        ttk.Button(self.main_frame, text="Complete Payment", 
                   command=self.complete_payment).pack(pady=20)

    def insert_coin(self, value):
        if self.balance + value > 1000:
            messagebox.showinfo("Error", "System balance is full.")
        else:
            self.balance += value
            self.show_payment()

    def complete_payment(self):
        if self.balance >= self.total_price:
            change = self.balance - self.total_price
            change_coins = self.calculate_change(change)
            change_str = ", ".join([f"{count} x {coin} บาท coin{'s' if count > 1 else ''}" for coin, count in change_coins.items() if count > 0])
            messagebox.showinfo("Payment Complete", f"Thank you for your purchase!\nYour change: {change} บาท\n({change_str})")
            self.balance = 0
            self.show_main_menu()
        else:
            messagebox.showinfo("Insufficient Funds", "Please insert more money.")

    def calculate_change(self, amount):
        coins = [100, 50, 20, 10, 5, 1]
        change = {}
        for coin in coins:
            count = amount // coin
            if count > 0:
                change[coin] = count
                amount %= coin
        return change

    def create_option_buttons(self, options, command): #option is menu  #command is select_rice_type function
        for option, price in options:
            ttk.Button(self.main_frame, text=f"{option}: {price:+d} บาท", 
                       command=lambda o=option, p=price: command(o, p)).pack(pady=5) #command(o,p) is call select_rice_type function and send argument o,p 

    def clear_frame(self):
        for widget in self.main_frame.winfo_children():
            widget.destroy()

if __name__ == "__main__":
    root = tk.Tk()
    app = ThaiVendingMachine(root)
    root.mainloop()
