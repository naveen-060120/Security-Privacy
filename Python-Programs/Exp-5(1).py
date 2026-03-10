import pandas as pd

data = pd.DataFrame({
    'Name': ['John Doe', 'Jane Smith', 'Johnson'],
    'Email': ['johndoe@example.com', 'janesmith@example.com', 'johnson@example.com'],
    'Age': [25, 30, 35]
})

data['Name'] = 'naveen karthick'
data['Email'] = 'naveenkarthick0710@gmail.com'

print(data)
