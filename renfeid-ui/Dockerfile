FROM node:16-alpine
WORKDIR /app

ENV NODE_ENV production

RUN addgroup --system --gid 1001 nodejs
RUN adduser --system --uid 1001 nextjs

COPY renfeid-ui/public ./public
COPY renfeid-ui/package.json ./package.json

COPY --chown=nextjs:nodejs renfeid-ui/.next ./.next

USER nextjs

EXPOSE 3000

ENV PORT 3000

CMD ["node", "server.js"]