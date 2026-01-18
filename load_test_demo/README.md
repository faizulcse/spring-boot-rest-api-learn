# load_test_demo

This folder contains k6 load test scripts.

## Prerequisites

- Node.js + npm
- k6 installed

## Run

You must run npm scripts from inside this folder:

```bash
cd load_test_demo
npm install
```

Run the script:

```bash
npm run test:script
```

Dashboard mode:

```bash
npm run test:dashboard
```

Note: the target URLs are defined in `utils/index.js` and loaded from `.env.test`.