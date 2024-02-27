from flask import Flask

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'YOYO Hello, World from Flask!'

@app.route('/<opt>/<float:a>/<float:b>')  # Modified route
def calculate(opt, a, b):
    if opt == "add":
        ans = a+b
        return f'<h3>{a} + {b} = {ans}</h3>'
    if opt == "sub":
        ans = a-b
        return f'<h3>{a} - {b} = .2f{ans}</h3>'
    if opt == "mul":
        ans = a*b
        return f'<h3>{a} * {b} = {ans}</h3>'
    if opt == "div":
        ans = a/b
        return f'<h3>{a} / {b} = {ans}</h3>'

if __name__ == '__main__':
    app.run(debug=True, port=5000)

