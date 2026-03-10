roles = { 
    "admin": ["read", "write", "delete"], 
    "developer": ["read", "write"], 
    "end_user": ["read"] 
} 
user_roles = { 
    "admin@cloud.com": "admin", 
    "dev@cloud.com": "developer", 
    "user@cloud.com": "end_user" 
}  
email = input("Enter your email: ") 
permission = input("Enter permission to check (read/write/delete): ") 
if email in user_roles: 
    role = user_roles[email] 
    if permission in roles[role]: 
        print("Access Granted") 
    else: 
        print("Access Denied") 
else: 
    print("User not found")
