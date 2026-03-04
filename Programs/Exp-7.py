from PIL import Image, ImageFilter 
image = Image.open("original_image.jpg") 
blurred_image = image.filter(ImageFilter.GaussianBlur(radius=40)) 
blurred_image.save("obfuscated_image.jpg") 
print("Image obfuscated successfully") 
