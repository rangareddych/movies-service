import boto3

import requests

def lambda_handler(event, context):
    #logging.warning('Warning - From Movies lambda_handler')  # will print a message to the console
    # Retrieve the S3 object key from the Lambda event
    object_key = event['Records'][0]['s3']['object']['key']

    url = "http://a15a8bea509b74e7594f3e35f1496280-497320279.us-east-1.elb.amazonaws.com/movies/file/"+object_key

    print("Movies service URL : "+url)
    response = requests.get(url)

    if response.status_code == 200:
        data = response.json()
        for item in data:
            print("Number of Movies inserted : "+item)
    else:
        print(f"Error: {response.status_code}")

    print(object_key)
    print("The End")