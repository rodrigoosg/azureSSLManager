#!/bin/bash

#
# This script is just an example on how SSL certificate generation and instalation could be automated in load balancers using
# CERTBOT - https://certbot.eff.org/docs/using.html and AWS CLI Tools - http://docs.aws.amazon.com/cli/latest/reference. 
# The steps executed here could also be implemented using AWS SDKs instead of AWS CLI Tools for example and other approaches should be considered if program becomes complex.
#

# FQDNset is the list of fully qualified domains that you want registered in the certificate. All domain names in that list must be pointing to the load balancer attached to the EC2 instance running this script. The limit of number of domains that a single certificate can have is 100 at the momment of this investigation. You can check for this and other limits at https://letsencrypt.org/docs/rate-limits/. This can be improved and this list could be read from another place (like a config file) or it can be even parametrized and don't need to be maintened in the script itself.
FQDNset="-d example.com -d www.example.com.br -d letsencrypt.example.com.br"

# This is te certificate identifiation to dierentiate among certificates uploaded to AWS IAM. This can be resolved retrieving existent certificates and can improve script automation.
DOMAIN_ADMIN_EMAIL=$1

if [ -z "$DOMAIN_ADMIN_EMAIL" ]
  then
    echo "Usage: registerDomain.sh www.example.com 25"
    exit 1;
fi
echo "Domain(s) to be registered: $FQDNset";

echo "Downloading certbot-auto...";
wget https://dl.eff.org/certbot-auto; chmod a+x ./certbot-auto;

# There are other validation methods you can use to generate the certificate. For this prototype we used WEBROOT method that relies on an apache server running to solve the challenge. For other methods and understanding o the other parameters see https://certbot.eff.org/docs/using.html
echo "Generating certificate for $FQDNset...";
./certbot-auto certonly --webroot -w /var/www/html/ $FQDNset --staging --email $DOMAIN_ADMIN_EMAIL --agree-tos --expand --debug --non-interactive;

echo "Done!"
