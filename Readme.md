Go to this github repo : https://github.com/camunda/camunda-platform#web-modeler-self-managed

Clone this Repo



After cloning this repo please run :

``docker compose up -d``

If you need docker download from here : https://docs.docker.com/desktop/install/mac-install/


After running the docker and making sure it is running 


Wait a few minutes for the environment to start up and settle down. Monitor the logs, especially the Keycloak container log, to ensure the components have started.

Now you can navigate to the different web apps and log in with the user demo and password demo:

Operate: http://localhost:8081

Tasklist: http://localhost:8082

Optimize: http://localhost:8083

Identity: http://localhost:8084

Elasticsearch: http://localhost:9200

Keycloak is used to manage users. Here you can log in with the user admin and password admin

Keycloak: http://localhost:18080/auth/
The workflow engine Zeebe is available using gRPC at localhost:26500.

To tear down the whole environment run the following command:

docker compose down -v


Please down load 

``https://camunda.com/download/modeler/``


Go to resources in the project 

You will see a sample.bpmn file

Upload open this file in the modular

The deploy it to self managed 

Follow this video from 5.14 min 

https://www.youtube.com/watch?v=uxRXc8v4bZY&t=526s


Run the spring boot application now

Use postman to call this api


http://localhost:8080/workflows

If you get an empty response without any error it means your app is working

You can check if the process is created by going here

Operate: http://localhost:8081

## Setup conductor

curl https://raw.githubusercontent.com/orkes-io/orkes-conductor-community/main/scripts/run_local.sh | sh

docker run --init -p 8080:8080 -p 5000:5000 conductoross/conductor-standalone:3.15.0

go to localhost:5000 to see if the ui is working


Install Mongo db 

https://www.mongodb.com/docs/mongodb-shell/install/



Run command in terminal 

db.createCollection("workflows")

WorkflowExecutionInterface is an interface which the workflow classes will implement to have the implementation of workflow specific to that workflow
example ```CamundaWorkflowExecutionImpl``` , ```ConductorWorkflowExecutionImpl```


```WorkflowExecutionFactory``` will return the type of workflow used using a database call based on the api

```WorkerResponseTranslationUtils``` is the translation layer which will convert the response from the workflow when we have the structure of the response finalized.

JSON for Camunda

```dtd
{
        "ownerApp": null,
        "createTime": 1718465549476,
        "updateTime": 1718464909124,
        "createdBy": null,
        "updatedBy": null,
        "accessPolicy": {},
        "name": "retrieve_food_data",
        "description": "Edit or extend this sample workflow. Set the workflow name to get started",
        "version": 1,
        "tasks": [
        {
        "name": "get_food_data",
        "taskReferenceName": "get_food_data",
        "description": "Get food data",
        "inputParameters": {
        "http_request": {
        "uri": "https://world.openfoodfacts.org/api/v0/product/737628064502.json",
        "method": "GET"
        }
        },
        "type": "HTTP",
        "dynamicTaskNameParam": null,
        "caseValueParam": null,
        "caseExpression": null,
        "scriptExpression": null,
        "dynamicForkJoinTasksParam": null,
        "dynamicForkTasksParam": null,
        "dynamicForkTasksInputParamName": null,
        "startDelay": 0,
        "subWorkflowParam": null,
        "sink": null,
        "optional": false,
        "taskDefinition": null,
        "rateLimited": null,
        "asyncComplete": false,
        "loopCondition": null,
        "retryCount": null,
        "evaluatorType": null,
        "expression": null
        }
        ],
        "inputParameters": [],
        "outputParameters": {
        "ingredients_text": "${get_food_data.output.response.body.product.ingredients_text}",
        "ingredients_original_tags": "${get_food_data.output.response.body.product.ingredients_original_tags}"
        },
        "failureWorkflow": null,
        "schemaVersion": 2,
        "restartable": true,
        "workflowStatusListenerEnabled": false,
        "ownerEmail": "example@email.com",
        "timeoutPolicy": "ALERT_ONLY",
        "timeoutSeconds": 0,
        "variables": {},
        "inputTemplate": {}
        }

```

Run Conductor 
Call the endpoint  /retrieve-food-data

