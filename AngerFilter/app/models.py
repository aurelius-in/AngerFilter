import openai
import os

# Set up OpenAI API credentials
openai.api_key = os.environ.get("OPENAI_API_KEY")

class AngerFilter:
    def __init__(self, prompt):
        self.prompt = prompt
        
    def generate_messages(self):
        """Generate alternative message suggestions using OpenAI's GPT-3.5 language model."""
        completions = openai.Completion.create(
            engine="text-davinci-002",
            prompt=self.prompt,
            max_tokens=256,
            n=3,
            stop=None,
            temperature=0.5,
        )

        # Extract message suggestions from API response
        messages = [choice.text.strip() for choice in completions.choices]
        
        return messages
