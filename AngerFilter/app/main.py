from flask import Flask, render_template, request
import gpt3_api_client



app = Flask(__name__)

# initialize GPT API client
gpt3_client = gpt3_api_client.GPT3APIClient()

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/generate', methods=['POST'])
def generate_message():
    # get user input from form
    user_input = request.form['user_input']

    # generate alternative messages using GPT
    alt_messages = gpt3_client.generate_alternatives(user_input)

    return render_template('alt_messages.html', alt_messages=alt_messages)

@app.route('/settings')
def settings():
    return render_template('settings.html')

@app.route('/about')
def about():
    return render_template('about.html')

if __name__ == '__main__':
    app.run(debug=True)
