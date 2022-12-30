FROM node:16-alpine
WORKDIR /app

ENV NODE_ENV production
ENV TZ="Asia/Shanghai"

RUN addgroup --system --gid 1001 nodejs
RUN adduser --system --uid 1001 nextjs

COPY public ./public

COPY --chown=nextjs:nodejs ./.next/standalone ./
COPY --chown=nextjs:nodejs ./.next/static ./.next/static

USER nextjs

RUN mkdir /tmp/upload

EXPOSE 3000

ENV PORT 3000

CMD ["node", "server.js"]