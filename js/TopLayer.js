import React, { Component } from 'react';
import codePush from 'react-native-code-push';
import {
  StyleSheet,
  Text,
  View,
  Button,
  DeviceEventEmitter,
} from 'react-native';

import ReactStore from './store';

const COLOR_CHANGE = 'COLOR_CHANGE';

class TopLayer extends Component {
  constructor(props) {
    super(props);
    this.state = { color: "black" }
  }
  componentDidMount() {
    DeviceEventEmitter.addListener(COLOR_CHANGE, this.colorChangeHandler);
  }
  componentWillUnmount() {
    DeviceEventEmitter.removeAllListeners(COLOR_CHANGE);
  }

  colorChangeHandler = event => this.setState({ color: event.color })

  render() {
    ReactStore.show("some", 12);
    return (
      <View
        collapsible="false"
        pointerEvents="box-none"
        style={styles.container}>
        <Button
          disabled={ this.state.color === 'red' ? true : false }
          title="set color Red"
          onPress={() => ReactStore.pushAction(COLOR_CHANGE, { colorName: "red" })}
        />
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={{ color: this.state.color, fontSize: 16 }}>
          To get started, edit index.android.js
        </Text>
        <Text style={styles.instructions}>
          Double tap R on your keyboard to reload,{'\n'}
          Shake or press menu button for dev menu
        </Text>
        <Button
          disabled={ this.state.color === 'green' ? true : false }
          title="set color Green"
          onPress={() => ReactStore.pushAction(COLOR_CHANGE, { colorName: "green" })}
        />
      </View>
    );
  }
}
export default codePush(TopLayer);

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
