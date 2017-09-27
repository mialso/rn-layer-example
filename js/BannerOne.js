import React, { Component } from 'react';
import codePush from 'react-native-code-push';
import {
  StyleSheet,
  Image,
  View,
  TouchableWithoutFeedback,
} from 'react-native';

import ReactStore from './store';

const BannerOne = () => {
  return (
    <View
      pointerEvents="box-none"
      style={styles.container}>
      <TouchableWithoutFeedback
        onPress={() => ReactStore.bannerClick("awesome banner one")}>
        <Image
          resizeMode="cover"
          style={{width: 180, height: 180}}
          source={require('../static/banner_one.png')}>
        </Image>
      </TouchableWithoutFeedback>
    </View>
  );
};

export default codePush(BannerOne);

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'flex-start',
    alignItems: 'flex-end',
  },
});
