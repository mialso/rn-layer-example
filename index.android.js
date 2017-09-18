/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import codePush from 'react-native-code-push';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Button
} from 'react-native';

class topLayer extends Component {
  constructor(props) {
    super(props);
    this.state = { color: "blue" }
  }
  render() {
    return (
      <View
        collapsible="false"
        pointerEvents="box-none"
        style={styles.container}>
        <Button title="R" onPress={() => this.setState({color: "red"})}>Red</Button>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={{ color: this.state.color }}>
          To get started, edit index.android.js
        </Text>
        <Text style={styles.instructions}>
          Double tap R on your keyboard to reload,{'\n'}
          Shake or press menu button for dev menu
        </Text>
        <Button title="B" onPress={() => this.setState({color: "blue"})}>Blue</Button>
      </View>
    );
  }
}
export default codePush(topLayer);

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    elevation: 0,
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('topLayer', () => topLayer);
