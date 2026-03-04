user_attributes = { 
    "user1": {"role": "Manager", "department": "Sales"}, 
    "user2": {"role": "Employee", "department": "Sales"}, 
    "user3": {"role": "Admin", "department": "IT"} 
}
policies = [ 
    {"resource": "sales_data", "action": "read", 
     "role": "Manager", "department": "Sales"}, 
    {"resource": "admin_panel", "action": "write", 
     "role": "Admin"} 
] 
user_id = input("Enter user ID: ") 
resource = input("Enter resource name: ") 
action = input("Enter action (read/write): ") 
access_granted = False 
if user_id in user_attributes: 
    user_role = user_attributes[user_id]["role"] 
    user_dept = user_attributes[user_id]["department"] 
    for policy in policies: 
        if policy["resource"] == resource and policy["action"] == action: 
            if (policy.get("role") == user_role and 
                policy.get("department", user_dept) == user_dept): 
                access_granted = True 
                break 
if access_granted: 
    print("Access Granted") 
else: 
    print("Access Denied")
