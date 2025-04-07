# Image-Dehazing-using-CycleGAN

### OBJECTIVE:
The main objective of our problem is to dehaze the hazed image using CycleGAN, with unpaired data for hazed and dehazed images, with just the limited data.

### DATASET:
https://drive.google.com/drive/folders/1ytLc1atUNpvauXHHXCQrN9vpw_dsGSow?usp=sharing

This dataset contains 51 hazed and 56 dehazed images.

### GAN OVERVIEW:
![image](https://user-images.githubusercontent.com/60288450/171109934-98fb7e0d-a92b-4c3b-a897-58f69f260109.png)

X is the random instance from real data, z is the random instance from random samples, G(z) is the generated output from random sample data. Discriminator’s output can be represented in two ways based on the type of the input it takes. If it takes real data as input, its output can be written as D(x) when the input data is the output of the generator; this can be written as D(G(z)).

Discriminator tries to maximize the probability that it predicts correctly, in other way, it tries to make D(G(z)) near to 0. Whereas the generator tries to minimize the probability that the discriminator predicting is correct, in other way, it tries to make D(G(z)) near to 1. 

#### Applications of GAN:

GAN has various number of use cases in real life scenarios,
●	Generate new data samples from available data – It means generating new samples from an available sample that is not similar to a real one.
●	Generate realistic pictures of people (Deepfakes) that have never existed.
●	GAN’s can generate not only image it can generate text, poems, musics, songs etc.,
●	GAN can also generate captions for images and videos by text to image generation using ( ObjectGAN and Object Driven GAN ). 
●	GAN can generate anime characters in Game Development and animation production on its own. 
●	GAN can translate one image to another without disturbing the background. ( In this project we have translated hazed images into dehazed images)
●	GAN also can be used to increase the resolution of the image or video (SR-GAN and ESR-GAN). 

### CycleGAN:

The CycleGAN is an extension of the GAN architecture that involves the simultaneous training of two generator models and two discriminator models. One generator takes images from the first domain (Hazed Images) as input and outputs images for the second domain (Dehazed Images), and the other generator takes images from the second domain (Dehazed Images) as input and generates images from the first domain (Hazed Images). Discriminator models are then used to determine how close the generated images are to the original image and update the generator models accordingly. 

Usually, GAN requires a dataset of paired examples to train an image-to-image translation model. It’s difficult to get pairwise datasets in real world datasets. In many cases such a dataset does not exist. Unpaired image-to-image translation is successfully done by cycle GAN. CycleGAN is well known for its application of image-to-image translation in the absence of paired data. 

![image](https://user-images.githubusercontent.com/60288450/171110966-33da85b9-400f-47a3-8c71-71b1b6ed17d9.png)


In our project we have two different collections of data (Hazed image and Dehazed image). For this we develop an architecture of two GANs, and each GAN has a discriminator and a generator model, meaning there are four models ( Generator A2B, Generator B2A, Discriminator B and Discriminator A) in total in the architecture. To summarize,

●	GAN 1: Translates dehazed Images to hazed Images.

●	GAN 2: Translates hazed Images to dehazed Images.
 
 We can summarize the generator and discriminator models from GAN 1 as follows:

Generator Model 1:
– Input: dehazed Images.
– Output: Generated hazed Images.
Discriminator Model 1:
– Input: hazed Images and output from Generator Model 1.
– Output: Likelihood of image is a hazed Image.

Similarly, we can summarize the generator and discriminator models from GAN 2 as follows:

Generator Model 2:
– Input: hazed Images.
– Output: Generated hazed Images.
 Discriminator Model 2:
– Input: dehazed Images and output from Generator Model 2.
– Output: Likelihood of image is a dehazed Image.


### Implementation of Cycle GAN for single image dehazing:
The the data is preprocessed in the way all the images are of the same shape i.e. (255,256,3).

### Input Image:

![image](https://user-images.githubusercontent.com/60288450/171112829-29c92cac-5dd7-45e2-9373-414e3e89f8c7.png)

### Discriminator:
![image](https://user-images.githubusercontent.com/60288450/171125302-b5f57a0c-5a86-4264-8317-a29338f4abdf.png)

### Generator:
![image](https://user-images.githubusercontent.com/60288450/171125943-94be932e-995a-497c-ae07-1329c3618126.png)

The parameters of the model are updated using:

●	Adversarial loss (L2 or mean squared error)

●	Identity loss (L1 or mean absolute error)

●	Forward cycle loss (L1 or mean absolute error)

●	Backward cycle loss (L1 or mean absolute error)

### Evaluation metrics:

A haze removal algorithm’s performance can be evaluated on several factors, among them, two of the most frequently used factors are the PSNR and SSIM. Peak Signal to Noise Ratio (PSNR) measures the ability of the algorithm to remove noise from a noisy image.

SSIM is also a full-reference evaluation method, which measures the similarity between the clean image and the image to be evaluated in terms of brightness, contrast, and structure. Two identical images will have a SSIM of 1.

### OUTPUT:

![image](https://user-images.githubusercontent.com/60288450/171126934-e351aff1-ee0c-477b-aa61-3a77af380fee.png)
![image](https://user-images.githubusercontent.com/60288450/171126984-bd1f031c-b040-4f41-9adc-0ad790187b0b.png)


### Conclusion:

The trained GAN was tested on the test dataset with 10 images. The average Peak Signal-to-Noise Ratio is 49.39523569512697 and Structural Similarity Index Measure is 0.8916414893280891. Our model works pretty well for dehazing the image without any loss in its spatial properties while removing the haze (noise), with just 107 training samples. 
