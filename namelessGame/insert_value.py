import mysql.connector

from mysql.connector import Error

selectedTable = None
confirm = None

try:
    # Configure database information
    connection = mysql.connector.connect(host = 'localhost',
                                         database = 'namelessGame',
                                         user = 'root',
                                         password='admin')

    if connection.is_connected():
        cursor = connection.cursor()

        while True:
            # "Clear" terminal
            print(chr(27) + "[2J")

            print("(1) - insert into item")
            print("(2) - insert into monster")
            print("(3) - insert into dungeon")
            print("(4) - quit")

            selectedTable = int(input())

            # "Clear" terminal
            print(chr(27) + "[2J")

            if selectedTable == 1:
                print("Inserting new item...")

                name = input("Name: ")
                icon = input("Icon (JUST file name): ")

                stackable = input("Stackable (1 -> yes, 0 -> no): ")

                strenght = input("Strenght: ")
                agility = input("Agility: ")
                constitution = input("Constitution: ")

                heal = input("Heal amount: ")

                slot = input("Item slot (0 if can't equip): ")
                minLv = input("Min. level: ")

                print("\nItem info:")
                print("Name: " + name)
                print("Icon: " + icon + ".png")
                print("Stackable: " + (int(stackable) == 1 and "yes" or "no"))

                if int(heal) > 0:
                    print("Heal amout: " + heal)
                else:
                    print("Strenght: " + strenght)
                    print("Agility: " + agility)
                    print("Constitution: " + constitution)
                
                if int(slot) != 0:
                    print("Slot: " + slot)

                print("Min. level: " + minLv)

                confirm = bool(input("Insert value? (1 -> yes, 0 -> no)"))

                if confirm:
                    cursor.execute("INSERT INTO item(str, agi, con, heal, slot, min_lv, name, icon, stackable) VALUES (" + strenght  + ", " + agility + ", " + constitution  + ", " + heal  + ", " + slot  + ", " + minLv  + ", '" + name  + "', '" + icon  + "', " + stackable + ");")
            
            elif selectedTable == 2:
                print("Inserting new monster...")
            elif selectedTable == 3:
                print("Inserting new dungeon...")
            else:
                break

            print("Success inserting new value.")
            
except Error as e :
    print("Error while connecting to MySQL", e)
finally:
    if connection.is_connected():
        cursor.close()
        connection.close()