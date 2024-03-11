# Stage 1: Build the Vue.js application
FROM node:14 as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Stage 2: Serve the application with Caddy
FROM caddy:2.3.0
LABEL authors="matis"

# Set Caddyfile (optional if you have specific configurations)
# COPY Caddyfile /etc/caddy/Caddyfile

# Copy the built application from the previous stage
COPY --from=build-stage /app/dist /usr/share/caddy

# Caddy runs on port 80 by default
EXPOSE 80
