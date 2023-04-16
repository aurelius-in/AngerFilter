from flask import Flask, request, render_template, redirect, url_for
from .models import AngerFilter

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
