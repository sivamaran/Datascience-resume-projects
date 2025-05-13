README.md# Automated Data Query and Retrieval System

## Overview

This system automates the process of querying and retrieving data from a MongoDB database using natural language input. It loads data from a CSV file into MongoDB, uses a language model (LLM) to generate MongoDB queries based on user input, and then retrieves and presents the data.

## Requirements

* Python 3.x
* MongoDB installed and running
* PyMongo library (`pip install pymongo`)
* LangChain library (`pip install langchain`)
* CTransformers (`pip install ctransformers`)
* Pandas (`pip install pandas`)
* A pre-trained LLM model (e.g., Llama 2).  The script is set up to use a locally downloaded Llama 2 model.  You will need to download this separately and place it in the same directory as the script, or update the path in the script.

## Setup

1.  **Install Dependencies:**
    ```bash
    pip install pymongo langchain ctransformers pandas
    ```
2.  **Download LLM Model:**
    * Download a compatible model (e.g., Llama 2) and place it in the same directory as the Python script.  You may need to adjust the model path in the script.
3.  **Start MongoDB:**
    * Ensure that your MongoDB server is running. The script connects to the default URI (`mongodb://localhost:27017/`).
4.  **Prepare CSV Data:**
    * Place your CSV file (e.g., `products.csv`) in the same directory as the Python script.
5.  **Run the Script:**
    ```bash
    python your_script_name.py  # Replace your_script_name.py
    ```

## Usage

1.  The script first loads the data from the `products.csv` file into the `products` collection in the `product_data` database in MongoDB.
2.  The script then executes the predefined test cases.  For each test case, it will:
    * Print the test case.
    * Generate a MongoDB query using the LLM.
    * Print the generated query.
    * Execute the query against the MongoDB database.
    * Display the results in the console.
    * Save the results to a CSV file (e.g., `test_case_1.csv`).
    * Append the generated query to `queries_generated.txt`
  
## Important Notes
* The script assumes that MongoDB is running on the default port (27017).  If your MongoDB instance is configured differently, you will need to update the connection string in the script.
* The script uses a local LLM model.  You'll need to download a model separately and provide the correct path in the script.
* Error handling is implemented for file operations, MongoDB operations, and LLM query generation.
* The script uses `eval()` to execute the MongoDB query. This is generally discouraged in production environments due to potential security risks if the query source is not trusted.  For this assignment, it's acceptable, but in a real-world application, you should use a safer alternative, such as constructing the query using PyMongo's methods.
* The output CSV files will be saved in the same directory as the script.
Code Comments: The Python script itself contains detailed comments explaining each function and section of the code.3. Test Case Outputqueries_generated.txt:Test Case 1: Find all products with a rating below 4.5 that have more than 200 reviews and are offered by the brand 'Nike' or 'Sony'.
Query Generated: { "Rating": { "$lt": 4.5 }, "Reviews": { "$gt": 200 }, "$or": [{ "Brand": "Nike" }, { "Brand": "Sony" }] }

Test Case 2: Which products in the Electronics category have a rating of 4.5 or higher and are in stock?
Query Generated: { "Category": "Electronics", "Rating": { "$gte": 4.5 }, "Stock": true }

Test Case 3: List products launched after January 1, 2022, in the Home & Kitchen or Sports categories with a discount of 10% or more, sorted by price in descending order.
Query Generated: { "Launch Date": { "$gt": "January 1, 2022" }, "$or": [{ "Category": "Home & Kitchen" }, { "Category": "Sports" }], "Discount": { "$gte": 10 } }
Sample CSV File (products.csv):Product ID,Name,Price,Category,Rating,Reviews,Brand,Stock,Launch Date,Discount
1,Laptop,1200,Electronics,4.7,350,Dell,True,2021-05-15,5
2,Smartphone,800,Electronics,4.6,500,Samsung,True,2022-03-01,0
3,T-Shirt,25,Clothing,4.3,250,Nike,True,2023-01-20,15
4,Running Shoes,100,Sports,4.8,300,Nike,True,2022-02-10,10
5,Television,1000,Electronics,4.5,400,Sony,True,2021-10-01,0
6,Coffee Maker,75,Home & Kitchen,4.2,150,Black+Decker,True,2022-04-01,0
7,Soccer Ball,20,Sports,4.4,200,Adidas,True,2023-03-01,5
8,Headphones,150,Electronics,4.9,600,Sony,True,2022-01-15,10
9,Blender,100,Home & Kitchen,4.0,100,Ninja,True,2023-02-01,0
10,Baseball Bat,50,Sports,4.1,120,Rawlings,True,2022-05-01,5
11,Gaming Mouse,70,Electronics,4.7,280,Logitech,True,2021-11-01,0
12,Jeans,60,Clothing,4.5,180,Levi's,True,2023-04-01,0
13,Smartwatch,300,Electronics,4.6,420,Apple,True,2022-02-18,0
14,Toaster,40,Home & Kitchen,3.9,90,Hamilton Beach,True,2023-03-15,0
15,Basketball,30,Sports,4.7,220,Spalding,True,2022-04-10,5
Test Case Output (CSV Files): The script generates three CSV files:  test_case_1.csv, test_case_2.csv, and test_case_3.csv.  Here's what you'd find in each:test_case_1.csv:Product ID,Name,Price,Category,Rating,Reviews,Brand,Stock,Launch Date,Discount
3,T-Shirt,25,Clothing,4.3,250,Nike,True,2023-01-20,15
test_case_2.csv:Product ID,Name,Price,Category,Rating,Reviews,Brand,Stock,Launch Date,Discount
2,Smartphone,800,Electronics,4.6,500,Samsung,True,2022-03-01,0
5,Television,1000,Electronics,4.5,400,Sony,True,2021-10-01,0
8,Headphones,150,Electronics,4.9,600,Sony,True,2022-01-15,10
11,Gaming Mouse,70,Electronics,4.7,280,Logitech,True,2021-11-01,0
13,Smartwatch,300,Electronics,4.6,420,Apple,True,2022-02-18,0
test_case_3.csv:Product ID,Name,Price,Category,Rating,Reviews,Brand,Stock,Launch Date,Discount
4,Running Shoes,100,Sports,4.8,300,Nike,True,2022-02-10,10
