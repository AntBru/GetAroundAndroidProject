#!/bin/bash

# -qq implies -y --force-yes

# Update repository
echo ">>> Update repositories"
sudo apt-get -qq update
echo ""

# Setting locale
echo ">>> Setting locale"
sudo apt-get install -qq language-pack-it
sudo locale-gen it_IT
sudo update-locale LANG=it_IT.UTF-8 LC_CTYPE=it_IT.UTF-8
echo ""

# Install base pkgs
echo ">>> Install base packages"
sudo apt-get install -qq curl zip unzip build-essential make vim colordiff
echo ""

# Install git
echo ">>> Install git"
sudo apt-get install -qq git-core
echo ""

# Installing apache
echo ">>> Installing Apache server"
sudo apt-get install -qq apache2
echo ""

# Installing MySQL
echo ">>> Installing MySQL"

sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password password root'
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password root'

sudo apt-get install -qq mysql-server
echo ""

# Installing PHP
echo ">>> Installing PHP"
sudo apt-get install -qq php5 php5-cli php5-common php5-fpm php5-mysql libapache2-mod-php5 php5-curl php5-mcrypt php-pear php5-dev
echo ""

# Install Composer
echo ">>> Installing Composer"
curl -sS https://getcomposer.org/installer | sudo php -- --install-dir=/usr/local/bin --filename=composer
echo ""

# Restart Apache
echo ">>> Restart Apache"
sudo /etc/init.d/apache2 restart
echo ""

# End
echo ">>> That's all folks!"
echo ""

