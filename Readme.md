


# Starting Parse Server
1. Open Terminal/cmd and navigate to the directory containing the .pem key file (from AWS)
2. Paste ssh -i "filename.pem" bitnami@ec2-18-119-207-157.us-east-2.compute.amazonaws.com and hit enter
3. After the Bitnami graphic shows,
    - type cd stack/parse hit enter
    - type cat config.json hit enter
    You get the app details
4. Add the Starter application class to the class package
5. Switch to the Project structure and navigate to app/build.gradle. Add parse dependencies
    //parse
       implementation 'com.parse.bolts:bolts-tasks:1.4.0'
       implementation 'com.parse:parse-android:1.13.0'

## Using Parse web interface
1. http://${18.119.207.157}/ OR http://127.0.0.1/parse replace with IP of server
2. Default username - user
3. Default password - $masterKey in Bitnami Server in CLI


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

Twitter Icon
Big icon - https://pixlok.com/images/twitter-splash-icon-png-image-free-download/
Little icon - <div>Icons made by <a href="https://www.flaticon.com/authors/pixel-buddha" title="Pixel Buddha">Pixel Buddha</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>