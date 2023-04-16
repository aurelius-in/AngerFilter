from flask import Flask, request, render_template, redirect, url_for
from .models import AngerFilter

# This implementation defines four routes for the app:

# / - The app's home page, which displays a form for users to enter their angry message input.
# /generate_messages - This route is called when the user submits their angry message. It uses the AngerFilter class to generate alternative message suggestions, and then renders the messages.html template to display the generated messages and the original angry message.
# /settings - This page displays the app's settings.
# /about - This page provides information about the app.

app = Flask(__name__)

@app.route("/")
def home():
    return render_template("index.html")

@app.route("/generate_messages", methods=["POST"])
def generate_messages():
    # Get user input from form submission
    angry_message = request.form["angry_message"]

    # Generate alternative message suggestions using AngerFilter class
    af = AngerFilter(angry_message)
    messages = af.generate_messages()

    # Render template with generated messages and original message
    return render_template("messages.html", messages=messages, original_message=angry_message)

@app.route("/settings")
def settings():
    return render_template("settings.html")

@app.route("/about")
def about():
    return render_template("about.html")
