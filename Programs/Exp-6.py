from Crypto.Cipher import AES 
from Crypto.Util.Padding import pad, unpad 
import os 
 
key = b'ThisIsASecretKey' 
with open("Original_Image.jpg", "rb") as file: 
    image_data = file.read() 
 
iv = os.urandom(16) 
cipher = AES.new(key, AES.MODE_CBC, iv) 
encrypted_data = cipher.encrypt(pad(image_data, AES.block_size)) 
 
with open("encrypted_image.enc", "wb") as file: 
    file.write(iv + encrypted_data) 
print("Image encrypted successfully") 
 
cipher = AES.new(key, AES.MODE_CBC, iv) 
decrypted_data = unpad(cipher.decrypt(encrypted_data), AES.block_size) 
with open("decrypted_image.jpg", "wb") as file: 
    file.write(decrypted_data) 
print("Image decrypted successfully") 
