import React, { Component } from 'react';
import codePush from 'react-native-code-push';
import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';

import ReactStore from './store';

const BannerButton = () => {
  return (
    <View
      pointerEvents="box-none"
      style={styles.container}>
      <TouchableOpacity onPress={() => ReactStore.bannerClick("awesome banner buttton")}>
      	<Text style={styles.buttonText}>"BannerButton"</Text>
      </TouchableOpacity>
    </View>
  );
};

export default codePush(BannerButton);

const styles = StyleSheet.create({
  container: {
    justifyContent: 'flex-start',
    alignItems: 'flex-start',
  },
    buttonText: {
        alignSelf: 'center',
        color: 'red',
        fontSize: 16,
        fontWeight: 'bold',
        padding: 20,
    },
});
