### This is postgres database installation on ubuntu machine.

1. Go to su mode.
    
        sudo su -

2. Run the below command to install the postgress | if not logged in as root user then append **sudo** in the installation cmd.

            apt-get update
            apt-get install postgresql postgresql-contrib
          
3. configure the postgres as service.

            update-rc.d postgresql enable

4. start service as deamon.

            service postgresql start
 
5. Connecto to postgres.

            sudo -u postgres psql
        
6. To create database: 

            create database <database_name>
            
7. To display database:
            
               \l
