import mysql.connector

from mysql.connector import Error

selectedTable = None
confirm = None

lootStr = ""
lootList = []

monsterStr = ""
monsterList = []

try:
    # Configure database information
    connection = mysql.connector.connect(host = 'localhost',
                                         database = 'namelessGame',
                                         user = 'root',
                                         password = 'admin')

    if connection.is_connected():
        cursor = connection.cursor()

        while True:
            print("\t(1) - insert into item")
            print("\t(2) - insert into monster")
            print("\t(3) - insert into dungeon")
            print("\t(4) - quit")

            selectedTable = int(input())

            if selectedTable == 1:
                print("\tInserting new item...\n")

                name = input("Name: ")
                icon = input("Icon (JUST file name): ")

                stackable = input("Stackable (1 -> yes, 0 -> no): ")

                strenght = input("Strenght: ")
                agility = input("Agility: ")
                constitution = input("Constitution: ")

                heal = input("Heal amount: ")

                slot = input("Item slot (0 if can't equip): ")
                minLv = input("Min. level: ")

                print("\n\t-- Item information --")
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

                confirm = int(input("\tInsert value? (1 -> yes, 0 -> no)\n"))

                if confirm == 1:
                    cursor.execute("INSERT INTO item(str, agi, con, heal, slot, min_lv, name, icon, stackable) VALUES (" + strenght  + ", " + agility + ", " + constitution  + ", " + heal  + ", " + slot  + ", " + minLv  + ", '" + name  + "', '" + icon  + "', " + stackable + ");")
                    connection.commit()

                    print("\tSuccess inserting new item... Type anything to continue.")
                    input()

            elif selectedTable == 2:
                print("\tInserting new monster...\n")

                lootList.clear()

                name = input("Name: ")
                icon = input("Icon (JUST file name): ")

                strenght = input("Strenght: ")
                agility = input("Agility: ")
                constitution = input("Constitution: ")

                experience = input("Exp. given: ")
                goldMin = input("Min. gold given: ")
                goldMax = input("Max. gold given: ")

                dungeonId = input("Dungeon ID: ")
                monsterRound = input("Round that the monster appear in its dungeon: ")

                print("\t-- Loot list (N-M) --")

                while True:
                    itemId = input("Item ID: ")

                    chance = input("Chance of looting (%): ")
                    countMin = input("Min. amount: ")
                    countMax = input("Max. amount: ")

                    print("\n\t-- Item information --")
                    print("ID: " + itemId)
                    print("Chance: " + chance + "%")
                    print("Amount: [" + countMin + ", " + countMax + "]")

                    confirm = int(input("\tInsert item? (1 -> yes, 0 -> no)\n"))

                    if confirm == 1:
                        lootList.append({
                            "id": itemId,
                            "chance": chance,
                            "countMin": countMin,
                            "countMax": countMax
                        })
                    
                    confirm = int(input("\tInsert new item? (1 -> yes, 0 -> no)\n"))

                    if confirm != 1:
                        break

                print("\n\t-- Monster information --")
                print("Name: " + name)
                print("Icon: " + icon + ".png")
                
                print("Strenght: " + strenght)
                print("Agility: " + agility)
                print("Constitution: " + constitution)

                print("Exp. given: " + experience)
                print("Gold given: [" + goldMin + ", " + goldMax + "]")
                
                print("Dungeon ID: " + dungeonId + ", appearing at round " + monsterRound)

                print("\t-- Loot list --")

                for item in lootList:
                    print("Item ID: " + item["id"])
                    print("Chance: " + item["chance"] + "%")
                    print("Amount: [" + item["countMin"] + ", " + item["countMax"] + "]")
                
                confirm = int(input("\tInsert value? (1 -> yes, 0 -> no)\n"))

                if confirm == 1:
                    cursor.execute("INSERT INTO monster(name, str, agi, con, exp, gold_min, gold_max, round, icon, dungeon_id) VALUES ('" + name + "', " + strenght  + ", " + agility + ", " + constitution  + ", " + experience + ", " + goldMin + ", " + goldMax + ", " + monsterRound + ", '" + icon + "', " + dungeonId + ");")

                    lootStr = "INSERT INTO monster_has_item(monster_id, item_id, count_min, count_max, chance) VALUES "                    

                    if len(lootList) > 0:                        
                        firstTime = True

                        for item in lootList:
                            if not firstTime:
                                lootStr += ", "
                            else:
                                firstTime = False

                            lootStr += "((SELECT MAX(id) FROM monster), " + item["id"] + ", " + item["countMin"] + ", " + item["countMax"] + ", " + item["chance"] + ")"

                        lootStr += ";"

                        cursor.execute(lootStr)

                    connection.commit()

                    print("\tSuccess inserting new monster... Type anything to continue.")
                    input()

            elif selectedTable == 3:
                print("\tInserting new dungeon...")

                monsterList.clear()

                name = input("Name: ")
                description = input("Dungeon description: ")
                background = input("Background (JUST file name): ")

                minLv = input("Min. level to access the dungeon: ")

                print("\n\t-- Dungeon information --")
                print("Name: " + name)
                print("Description: " + description)
                print("Background: " + background + ".png")

                print("Min. level: " + minLv)

                confirm = int(input("\tInsert value? (1 -> yes, 0 -> no)\n"))

                if confirm == 1:
                    cursor.execute("INSERT INTO dungeon(name, descr, min_lv, background) VALUES ('" + name + "',  '" + description + "', " + minLv + ", '" + background + "');")
                    connection.commit()

                    print("\tSuccess inserting new dungeon... Type anything to continue.")
                    input()
                
            else:
                break
            
except Error as e :
    print("Error while connecting to MySQL", e)
finally:
    if connection.is_connected():
        cursor.close()
        connection.close()
