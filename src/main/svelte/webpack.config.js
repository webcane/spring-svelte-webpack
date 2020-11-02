const path = require('path');
const merge = require('webpack-merge');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

const TARGET = process.env.npm_lifecycle_event;
const PATHS = {
    source: path.join(__dirname, 'src'),
    output: path.join(__dirname, '../../../target/classes/static')
};

const mode = process.env.NODE_ENV || 'development';
const prod = mode === 'production';

const common = {
    entry: // [    PATHS.source  ]
        {
            bundle: ['./src/index.js'],
        },
    output: {
        path: PATHS.output,
        publicPath: '',
        filename: 'bundle.js',
        chunkFilename: '[name].[id].js'
    },
    module: {
        // loaders: [{
        //   exclude: /node_modules/,
        //   loader: 'babel'
        // }, {
        //   test: /\.css$/,
        //   loader: 'style!css'
        // }]
        rules: [
            {
                test: /\.svelte$/,
                use: {
                    loader: 'svelte-loader',
                    options: {
                        emitCss: true,
                        hotReload: true
                    }
                }
            },
            {
                test: /\.css$/,
                use: [
                    /**
                     * MiniCssExtractPlugin doesn't support HMR.
                     * For developing, use 'style-loader' instead.
                     * */
                    prod ? MiniCssExtractPlugin.loader : 'style-loader',
                    'css-loader'
                ]
            }
        ]
    },
    resolve: {
        alias: {
            svelte: path.resolve('node_modules', 'svelte')
        },
        extensions: ['.mjs', '.js', '.svelte'],
        mainFields: ['svelte', 'browser', 'module', 'main']
    },
    mode,
    plugins: [
        new MiniCssExtractPlugin({
            filename: '[name].css'
        })
    ]
};

if (TARGET === 'start' || !TARGET) {
    module.exports = merge(common, {
        devServer: {
            port: 3000,
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, PATCH, OPTIONS",
                "Access-Control-Allow-Headers": "X-Requested-With, content-type, Authorization"
            },
            proxy: {
                '/': {
                    target: 'http://localhost:8080',
                    changeOrigin: true
                }
            },
            publicPath: 'http://localhost:3000/',
            historyApiFallback: true
        },
        devtool: 'source-map'
    });
}

if (TARGET === 'build') {
    module.exports = merge(common, {});
}