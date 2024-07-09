const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});

readline.question('Input Word minimum 10 letter.-->', wordInput => {
    let length = wordInput.length;
    let i = 0;
    while (i < length) {
        let wave = [];
        let k = 0;
        while (k < length) {
            if (k === i) {
                wave.push(wordInput.charAt(k).toUpperCase());
            }
            else {
                wave.push(wordInput.charAt(k).toLowerCase());
            }
            k++;
        }

        let wordOutput = '';
        for (let letter of wave) {
            wordOutput += letter;
        }
        console.log(wordOutput);
        i++;
    }
    readline.close();
});
