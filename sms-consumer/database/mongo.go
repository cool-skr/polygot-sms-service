package database

import (
	"context"
	"fmt"

	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

func ConnectMongo() (*mongo.Collection, error) {

	client, err := mongo.Connect(
		context.Background(),
		options.Client().ApplyURI("mongodb://localhost:27017"),
	)

	if err != nil {
		return nil, err
	}

	fmt.Println("Connected to MongoDB")

	collection := client.
		Database("smsdb").
		Collection("messages")

	return collection, nil
}