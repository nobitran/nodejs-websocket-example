FROM node:18-alpine3.15

RUN mkdir -p /home/app

WORKDIR /home/app

COPY . /home/app/

RUN npm install

CMD ["node", "server.js"]

EXPOSE 3000 40510
