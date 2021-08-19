


Starting Parse Server in Terminal
1. Navigate to the directory containing the .pem key file (from AWS)
2. Paste ssh -i "filename.pem" bitnami@ec2-18-119-207-157.us-east-2.compute.amazonaws.com and hit enter
3. After the Bitnami graphic shows,
    - type cd stack/parse hit enter
    - type cat config.json hit enter
    You get the app details


NOTE:
Public IP always change when the Instance is restarted
Use the AMI name as user name and not admin or ec2-user or
 whatever amazon gives as example eg bitnami@ec2-25-2333...

For Windows, set the security policy from file properties. Remove all
 other users and make the owner the only one with "Read" access to the file
 Also check online for the use of icals in place of linux "chmod" command

After the Bitnami graphic shows,
 - type cd stack/parse hit enter
 - type cat config.json hit enter
