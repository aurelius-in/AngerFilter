from flask import Flask, render_template, request
import gpt3_api_client

# This is a Python Flask web application that 
# uses the OpenAI GPT-3 API to generate 
# alternative messages based on # user input. 
# The application has four routes:

# '/' : This route returns the index.html template, 
# which contains a form for the user to enter input.

# '/generate': This route is called when the form is 
# submitted. It extracts the user input from the form 
# and passes it to the GPT-3 API client to generate 
# alternative messages. It then returns the alt_messages.html 
# template, which displays the generated messages.
# alt_messages.html template, which displays the generated messages.

# '/settings': This route returns the settings.html template, which 
# could contain options for the user to customize the GPT-3 API client's behavior.

# '/about': This route returns the about.html template, which could 
# contain information about the application and its developers.

# The GPT-3 API client is initialized outside of any route to ensure 
# that it is only initialized once when the application starts.
# This avoids unnecessary API calls and improves the application's performance. 
# The debug flag is set to True in the app.run() method to enable debug mode.
# This should be turned off in production to avoid security risks.

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
