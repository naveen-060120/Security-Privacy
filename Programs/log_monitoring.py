logs = [ 
    "2024-01-10 10:20:30 INFO User login successful", 
    "2024-01-10 10:22:15 ERROR Database connection failed", 
    "2024-01-10 10:25:40 INFO File uploaded", 
    "2024-01-10 10:30:05 UNAUTHORIZED access attempt detected" 
] 
incident_id = 1 
print("Log Monitoring Started...\n")  
for log in logs: 
    if "ERROR" in log or "FAILED" in log or "UNAUTHORIZED" in log: 
        print("Incident Generated") 
        print("Incident ID:", incident_id) 
        print("Log Details:", log) 
        print("-" * 40) 
        incident_id += 1
