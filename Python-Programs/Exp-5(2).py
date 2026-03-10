import pandas as pd 
 
data = pd.DataFrame({ 
    'Name': ['John Doe', 'Jane Smith', 'Michael Johnson'], 
    'Zip Code': ['12345', '67890', '54321'], 
    'Age': [25, 30, 35] 
}) 
 
data['Name'] = 'Anonymous' 
data['Zip Code'] = 'XXXXX' 
print(data)
