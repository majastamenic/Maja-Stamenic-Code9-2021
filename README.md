# Code 9 - 2021 - Tennis scheduler 

> A standalone project that simulates the reservation of tennis courts. 
> It was made on request Code9 - 2021.

[Tennis scheduler - Trello](https://trello.com/b/2WjCcrGP/code9-2021)

### Microservices:
| Name | Port | About
| ------ | ------ | ------ |
| User-microservice | 9090 | Admin and tennis player |
| Tennis-court-microservice | 9099 | Tennis court reservation |
| Pay-microservice | 9990 | Payment |
| Notification-microservice | 9999 | Email service |

 ## Technologies:
 

 - Java 11
 - Spring boot
 - Spring data
 - Spring security
 - Spring cloud
 - Maven

## Tennis scheduler

 -   With tennis scheduler you should be able to create a multiple tennis court.
    
-   Each tennis court has its unique name
    
-   Tennis scheduler has a list of tennis players
    
-   Tennis scheduler is accessible to everyone, there is no need to sign in to use the app.
    
-   Tennis player needs to provide first name, last name, date of birth and email address so they can join the tennis player list
    
-   Tennis player list is accessible to everyone with the constraints
    
    -   that it needs to contain unique email
        
    -   Once tennis player is added to the list it cannot be modified or removed from the list by other tennis players
        
-   Administrator is the user who has all the rights
    
    -   He can remove tennis players from the list
        
-   Tennis court can be created only by Administrator
    
-   Tennis players can see all the courts, but they cannot remove them.
    
-   Administrator has a right to remove the tennis court.
    
-   Each tennis court has timeslots
    
-   To reserve a timeslot on the court we should be able to pick a tennis player from the list and choose the start date of reservation and end date of reservation. Period cannot be longer than 2 hours. Period cannot be less than 30 minutes.
    
-   You cannot reserve a time slot in the past. Only for the current date and days in the future.
    
-   On working days, you can register the slot from 18h to 23h.
    
-   Working time on weekends is from 17h to 22h.
    
-   You cannot have overlapping with timeslots
    
-   Once the timeslot is reserved it cannot be modified by any tennis player.
    
-   Administrator can remove any time slot
    
-   You cannot reserve two timeslots same day for the same tennis player
    

-   Tennis player can reserve multiple timeslots but not in the same day with some conditions.
    
    -   If Tennis player want to reserve more than 5 slots. Tennis player needs to pay a small fee of 10 euros
        
    -   Currently he has two payment options to choose from.
        
        -   Pay by cash
            
        -   Pay by credit card
            
-   Credit card information consist of credit card number, valid date and cvc code
    
-   Be aware that in the future we will have more payment options
    
-   Each time tennis player reserves a timeslot we will need to notify a group of people. It is not determined who specifically we need to notify. All we know that those people are not related but they expect to receive the same massage. For now, we need to store all reservations in the database.